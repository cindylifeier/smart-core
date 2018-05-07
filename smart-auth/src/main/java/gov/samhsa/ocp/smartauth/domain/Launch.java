package gov.samhsa.ocp.smartauth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "launches", timeToLive = 60 * 5)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Launch {
    @Id
    private String id;

    @Indexed
    private String user;

    @Indexed
    private String organization;

    @Indexed
    private String location;

    @Indexed
    private String patient;

    @Indexed
    private String encounter;

    @Indexed
    private String resource;

    public String getValueByContext(Context context) {
        switch (context) {
            case user:
                return getUser();
            case organization:
                return getOrganization();
            case location:
                return getLocation();
            case patient:
                return getPatient();
            case encounter:
                return getEncounter();
            case resource:
                return getResource();
            default:
                throw new IllegalArgumentException("context=" + context.name() + " is not supported by " + this.getClass().getSimpleName());
        }
    }
}
