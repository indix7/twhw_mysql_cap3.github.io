package work.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("teacher_subject")
public class TeacherWithSubject {
    @Id
    private int no;
    @Column("teacher_id")
    private int teacherId;
    @Column("subject_id")
    private int subjectId;

    public TeacherWithSubject(int no, int teacherId, int subjectId) {
        this.no = no;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
