package wowmarket.wow_server.univ.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UnivRequestDto {
    @Pattern(regexp = ".*대학(교)?$")
    private String univ_name;

    @Email @NotBlank
    private String univ_email;
}
