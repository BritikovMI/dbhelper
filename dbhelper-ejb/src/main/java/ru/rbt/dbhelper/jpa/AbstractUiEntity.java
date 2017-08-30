package ru.rbt.dbhelper.jpa;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by KryukovMV on 01.03.2017.
 */
@MappedSuperclass
public abstract class AbstractUiEntity<I extends Serializable>
        extends AbstractEntity<I> implements DbhelperUiEntity<I>, Serializable {

    private static final long serialVersionUID = 4117067012142376282L;

    protected String name;

    @Override
    @NotNull
    @Size(min = 0, max = 255)
    @Column(name = "NM", length = 255, nullable = false, unique = true)
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

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
        AbstractUiEntity that = (AbstractUiEntity) o;
        return !(getId() != null ? !getId().equals(that.getId()) : that.getId() != null);
    }
}
