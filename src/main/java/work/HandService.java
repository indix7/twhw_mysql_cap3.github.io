package work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.entity.*;
import work.repositorty.*;

import java.util.*;

@Service
public class HandService {
    final CourseRepository courseRepository;

    final StudentRepository studentRepository;
    final SubjectRepository subjectRepository;
    final TeacherRepository teacherRepository;
    final TeacherWithSubjectRepository teacherWithSubjectRepository;
    final LoginRepository loginRepository;

    public HandService(CourseRepository courseRepository,
                       StudentRepository studentRepository, SubjectRepository subjectRepository,
                       TeacherRepository teacherRepository, TeacherWithSubjectRepository teacherWithSubjectRepository, LoginRepository loginRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.teacherWithSubjectRepository = teacherWithSubjectRepository;
        this.loginRepository = loginRepository;
    }

    public String getAllStudentInformation() {
        StringBuilder stringBuilder = new StringBuilder();
        studentRepository.findAll().forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    public String getStudentInfoAndScoreByName(String name) {
        Student student = studentRepository.getStudentByName(name);
        int id = student.getId();
        List<Course> courseList = courseRepository.getCoursesByStudentId(id);
        StringBuilder stringBuilder = new StringBuilder();
        for (Course course : courseList) {
            stringBuilder.append(student)
                    .append(" , 科目 : ")
                    .append(subjectRepository.findById(id).orElse(null))
                    .append(", 成绩 : ")
                    .append(course.getScore())
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    public String getStudentsInfoByTeacherName(String name) {
        int id = teacherRepository.getTeacherByName(name).getId();
        List<Course> courseList = courseRepository.getCoursesByTeacherId(id);
        StringBuilder stringBuilder = new StringBuilder();
        for (Course course : courseList) {
            stringBuilder.append("学生 : ")
                    .append(name)
                    .append(" , 科目 : ")
                    .append(subjectRepository.findById(id).orElse(null))
                    .append(", 成绩 : ")
                    .append(course.getScore())
                    .append("\n");
        }
        return stringBuilder.toString();
    }


    public String getAllSubject() {
        StringBuilder stringBuilder = new StringBuilder();
        subjectRepository.findAll().forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    public String getSubjectByName(String name) {
        return subjectRepository.getSubjectByName(name).toString();
    }

    public String getSubjectsByTeacherName(String name) {
        int id = teacherRepository.getTeacherByName(name).getId();
        List<TeacherWithSubject> list = teacherWithSubjectRepository.getTeacherWithSubjectByTeacherId(id);
        StringBuilder stringBuilder = new StringBuilder(name + "老师的课程为 ： \n");
        list.forEach(teacherWithSubject -> stringBuilder.append(teacherRepository.findById(teacherWithSubject.getTeacherId())));
        return stringBuilder.toString();
    }

    public String getAllTeacher() {
        StringBuilder stringBuilder = new StringBuilder();
        teacherRepository.findAll().forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    public String getTeacherByName(String name) {
        return teacherRepository.getTeacherByName(name).toString();
    }

    public void addStudent(String studentString) {
        String[] strings = Arrays.stream(studentString.split(","))
                .map(s -> s.split(":")[1])
                .toArray(String[]::new);
        studentRepository.saveStudent(Integer.parseInt(strings[0]), strings[1], Integer.parseInt(strings[2]), strings[3]);
    }

    public void addSubject(String subjectString) {
        String[] strings = Arrays.stream(subjectString.split(","))
                .map(s -> s.split(":")[1])
                .toArray(String[]::new);
        subjectRepository.saveSubject(Integer.parseInt(strings[0]), strings[1]);
    }

    public void modifyScoreByNameAndSubject(String string){
        //"name:name, subject:subject, score:score"
        String[] strings = Arrays.stream(string.split(","))
                .map(s -> s.split(":")[1])
                .toArray(String[]::new);
        courseRepository.modifyScoreByIdAndSubjectId(studentRepository.getStudentByName(strings[0]).getId(),
                subjectRepository.getSubjectByName(strings[1]).getId(), Double.parseDouble(strings[2]));
    }

    public String deleteStudentByName(String name) {
        Student student = studentRepository.getStudentByName(name);
        String returnInfo = student.getId() + "," + student.getName();
        studentRepository.delete(studentRepository.getStudentByName(name));
        return returnInfo;
    }

    public String deleteStudentById(String idString) {
        int id = Integer.parseInt(idString);
        Student student = studentRepository.findById(id).orElse(null);
        assert student != null;
        String returnInfo = student.getId() + "," + student.getName();
        studentRepository.delete(Objects.requireNonNull(studentRepository.findById(id).orElse(null)));
        return returnInfo;
    }

    public String deleteSubjectByName(String name) {
        Subject subject = subjectRepository.getSubjectByName(name);
        String info = subject.getId() + ", " + subject.getName();
        subjectRepository.delete(subjectRepository.getSubjectByName(name));
        return info;
    }

    public String deleteTeacherByName(String name) {
        Teacher teacher = teacherRepository.getTeacherByName(name);
        String info = teacher.getId()+ ", " + teacher.getName();
        teacherRepository.delete(teacherRepository.getTeacherByName(name));
        return info;
    }

    public String getAuthorityByIdAndPassword(String id, String password){
        return loginRepository.getLoginByIdAndPassword(id, password).getAuthority();
    }

}
