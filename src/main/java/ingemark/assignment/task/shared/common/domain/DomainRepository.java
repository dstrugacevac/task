package ingemark.assignment.task.shared.common.domain;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DomainRepository<DOMAIN, ID> {

    Optional<DOMAIN> findById(ID id);

    Optional<DOMAIN> save(DOMAIN domain);

    List<DOMAIN> fetch(Pageable pageable, String filter, String query);

    Long total(String filter, String query);

    void delete(UUID id);

    Optional<DOMAIN> update(DOMAIN domain);


}
