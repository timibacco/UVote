package votingme.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Entity
@RequiredArgsConstructor
@Table(name = "users")
public class Participant implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Email(message = "Email cannot be empty", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String firstname;
    private String lastname;


    @ManyToMany(cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();


    @JsonIgnore
    @OneToOne
    private Candidate candidateAccount;

    @JsonIgnore
    @OneToOne
    private Voter voterAccount;


    @JsonIgnore
    @OneToOne
    private Organizer organizerAccount;


    @JsonProperty("myAccount")
    public Object fetchAccount() {
    	if (candidateAccount != null) {
    		return candidateAccount;
    	} else if (voterAccount != null) {
    		return voterAccount;
    	} else if (organizerAccount != null) {
    		return organizerAccount;
    	}
    	return null;
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // List<GrantedAuthority> authorities = new ArrayList<>();
       return roles
               .stream()
               .map(role -> new SimpleGrantedAuthority(role.getName().name()))
               .collect(Collectors.toList());


    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
