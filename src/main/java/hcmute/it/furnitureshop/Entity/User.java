package hcmute.it.furnitureshop.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import hcmute.it.furnitureshop.Common.RankEnum;
import hcmute.it.furnitureshop.Common.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(unique = true)
    private String username;
    private String password;
    private String name;
    private String image;
    private String apartmentNumber;
    private String city;
    private String district;
    private String ward;
    private Date createDate;
    private String phone;
    private String status;
    private int point;

    @Enumerated(EnumType.STRING)
    private RankEnum rankUser;

    @Enumerated(EnumType.STRING)
    private RoleEnum role = RoleEnum.USER;

    @JsonManagedReference
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<Order> orders;

    @JsonBackReference
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<Review> reviews;

    @JsonBackReference
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<ResponseReview> responseReviews;
    @JsonBackReference
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<Rating> ratings;


    @JsonBackReference
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<Favorite> favorites;

    @JsonManagedReference
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<Notification> notifications;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}