package wowmarket.wow_server.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Blob;
import java.time.LocalDate;

@Entity
@Getter
public class Project extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "project_id")
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String description;

    private LocalDate start_date;
    private LocalDate end_date;
    private int participant_number;
    private Boolean is_del;
    private Boolean is_end;

//    private Blob thumbnail;
//    private Blob image1;
//    private Blob image2;
//    private Blob image3;
    private String receive_type;
    private String bank;
    private String account;
    private String inquired_phone;
    private String inquired_email;

    private int re_progress;
    private Long final_achievement_rate;

}
