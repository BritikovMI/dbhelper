package ru.rbt.dbhelper.jpa;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by KryukovMV on 01.03.2017.
 */
@MappedSuperclass
public abstract class AbstractDbhelperEntity<ID extends Serializable> implements DbhelperEntity<ID>, Serializable {

    private static final long serialVersionUID = 2777749923486209749L;

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.getClass().getName())
                .append("{id=")
                .append(getId())
                .append("}")
                .toString();
    }

    @Override
    public int hashCode() {
        return getId() == null ? 0 : getId().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractDbhelperEntity that = (AbstractDbhelperEntity) o;
        return !(getId() != null ? !getId().equals(that.getId()) : that.getId() != null);
    }
}
