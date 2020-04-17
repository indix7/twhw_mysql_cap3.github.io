package work.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("course")
public class Course {
    @Id
    private int no;
    @Column("student_id")
    private int studentId;
    @Column("teacher_id")
    private int teacherId;
    @Column("subject_id")
    private int subjectId;
    private double score;

    public Course(int no, int studentId, int teacherId, int subjectId, double score) {
        this.no = no;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
        this.score = score;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Course{" +
                "no=" + no +
                ", studentId=" + studentId +
                ", teacherId=" + teacherId +
                ", subjectId=" + subjectId +
                ", score=" + score +
                "}\n";
    }
}
