package wowmarket.wow_server.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import wowmarket.wow_server.global.jwt.JwtAuthenticationFilter;
import wowmarket.wow_server.global.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;


    @Bean //비밀번호 암호화 처리하는 메서드 제공
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests() // 요청에 대한 접근 권한을 설정하는 메서드
                .requestMatchers("/mypage/**").authenticated()
                .requestMatchers("/register/**").authenticated()
                .anyRequest().permitAll();
        http
//                .cors(cors -> cors.disable()) //프론트와 백엔드 협업에서 하는 설정 잠시 비활성화
                .csrf(csrf -> csrf.disable()) // token을 사용하는 방식이기 때문에 csrf를 disable로 설정
                .formLogin(formLogin -> formLogin.disable()) // 사용자 지정 로그인 로직 구현
                .httpBasic(httpBasic -> httpBasic.disable()); // http 기본 인증 비활성화


        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class);

        // 세션을 사용하지 않기 때문에 STATELESS로 설정
        http.sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // enable Mysql-console
        http.headers(headers ->
                headers.frameOptions(options ->
                        options.sameOrigin()
                )
        );
        return http.build();
    }

}
