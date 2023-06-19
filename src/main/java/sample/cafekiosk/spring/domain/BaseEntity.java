package sample.cafekiosk.spring.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * [MappedSuperclass 애노테이션]
 * 해당 어노테이션이 적용된 클래스는 테이블로 생성되지 않는다.
 * 이 클래스를 상속받은 엔티티에 매핑되는 테이블에 아래 있는 변수들이 생성된다. (부모 클래스로만 사용된다. Entity 클래스가 아님)
 *
 * [EntityListener()]
 * JPA에서는 엔티티 객체에 어떠한 변화가 생기는 것을 감지하는 리스너가 있다.
 * JPA 내부에서 엔티티 객체가 생성/변경되는 것을 감지하는 역할은 AuditingEntityListener가 한다.
 * 이를 통해 아래 있는 변수들에  적절한 값이 할당된다.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime createDateTime;

    @LastModifiedDate
    private LocalDateTime modifiedDateTime;
}
