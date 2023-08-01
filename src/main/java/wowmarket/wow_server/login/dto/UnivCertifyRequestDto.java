package wowmarket.wow_server.login.dto;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UnivCertifyRequestDto { //univCert로 보내는 request
    private String key; //univCertAPI
    private String univName;
    private String email; //univ_email;
    private boolean univ_check; //true : 대학 재학, false : 메일 소유

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}