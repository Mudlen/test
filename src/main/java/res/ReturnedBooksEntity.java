package res;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "returned_books", schema = "library", catalog = "")
public class ReturnedBooksEntity {
    private int id;
    private int id_book;
    private int id_student;
    private Timestamp data;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "data", nullable = false)
    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReturnedBooksEntity that = (ReturnedBooksEntity) o;
        return id == that.id && Objects.equals(data, that.data);
    }

    @Column(name = "id_book", nullable = false)
    public int getId_book() {
        return id_book;
    }
    public void setId_book(int id_book) {
        this.id_book = id_book;
    }
    @Column(name = "id_student", nullable = false)
    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data);
    }
}
