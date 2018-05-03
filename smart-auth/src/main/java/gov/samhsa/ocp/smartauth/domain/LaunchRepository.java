package gov.samhsa.ocp.smartauth.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LaunchRepository extends CrudRepository<Launch, String> {
    default Optional<Launch> findById(String id) {
        return Optional.ofNullable(findOne(id));
    }
}
