package work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class Console {
    final
    HandService handService;
    public Console(HandService handService){
        this.handService = handService;
        process();
    }


    private void process(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(getWelcomeInter());
        String idAndPassword = scanner.nextLine();
        String id = idAndPassword.split(":")[0];
        String password = idAndPassword.split(":")[1];
        if (!handService.getAuthorityByIdAndPassword(id, password).equals("root")){return; }
        while(true){
            try{
                System.out.println(getMainInter());
                System.out.println(handProcess(scanner.nextLine()));
            } catch (NullPointerException e){
                System.out.println("你键入的信息不存在，请确认后再次输入");
            }

        }
    }

    private String handProcess(String command){
        Scanner scanner = new Scanner(System.in);
        switch (command){
            case "1.1.1":
                return handService.getAllStudentInformation();
            case "1.1.2":
                System.out.println("请输入学生姓名：");
                return handService.getStudentInfoAndScoreByName(scanner.nextLine());
            case "1.1.3":
                System.out.println("请输入老师姓名：");
                return handService.getStudentsInfoByTeacherName(scanner.nextLine());
            case "1.2.1":
                return handService.getAllSubject();
            case "1.2.2":
                System.out.println("请输入课程名称：");
                return handService.getSubjectByName(scanner.nextLine());
            case "1.2.3":
                System.out.println("请输入老师姓名：");
                return handService.getSubjectsByTeacherName(scanner.nextLine());
            case "1.3.1":
                return handService.getAllTeacher();
            case "1.3.2":
                System.out.println("请输入老师姓名：");
                return handService.getTeacherByName(scanner.nextLine());
            case "2.1":
                System.out.println("请输入学生信息(例如：学号:1001,姓名:小明,年龄:18,性别:男)：   (请使用英文标点)");
                handService.addStudent(scanner.nextLine());
                return "添加成功";
            case "2.2":
                System.out.println("请输入课程信息(例如：课程编号:1001,课程名:math)     (请使用英文标点)");
                handService.addSubject(scanner.nextLine());
                return "添加成功";
            case "3.1":
                System.out.println("请输入学生姓名科目及成绩(例如:学生姓名:小明,科目:math,成绩:70)    (请使用英文标点)");
                try{
                    handService.modifyScoreByNameAndSubject(scanner.nextLine());
                }catch (NullPointerException e){
                    return "修改失败！学生或科目不存在，或该学生没有该科目！";
                }
                return "修改成功";
            case "4.1":
                System.out.println("请输入学生学号：");
                String id = scanner.nextLine();
                System.out.println("删除学生之后，该学生信息将不能恢复，是否要继续删除？\n1.是\n2.否");
                if (scanner.nextLine().equals("1")){
                    return "删除学生【" +  handService.deleteStudentById(id) + "】成功";
                }
                return "";
            case "4.2":
                System.out.println("请输入课程名：");
                String subjectName = scanner.nextLine();
                System.out.println("删除课程之后，该课程信息将不能恢复，是否要继续删除？\n1.是\n2.否");
                if (scanner.nextLine().equals("1")){
                    return "删除课程【" +  handService.deleteSubjectByName(subjectName) + "】成功";
                }
                return "";
            case "4.3":
                System.out.println("请输入教师姓名：");
                String teacherName = scanner.nextLine();
                System.out.println("删除教师之后，该教师信息将不能恢复，是否要继续删除？\n1.是\n2.否");
                if (scanner.nextLine().equals("1")){
                    return "删除教师【" +  handService.deleteTeacherByName(teacherName) + "】成功";
                }
                return "";
            default:
                System.out.println("请重新输入命令！");
                return "";
        }
    }

    private String getWelcomeInter() {
        return "您好，欢迎登陆学生考试系统，请输入您的用户名和密码(用户名:密码)：  (请使用英文符号)";
    }

    private String getMainInter() {
        return "您好，超级管理员，请选择你需要进行的操作：\n" +
                "    1. 查询   \n" +
                "      1.1 查询学生信息以及成绩  \n" +
                "        1.1.1 所有学生信息  \n" +
                "        1.1.2 指定学生姓名的信息以及所有课程的成绩  \n" +
                "        1.1.3 指定老师的所有学生及其成绩  \n" +
                "      1.2 查询课程信息  \n" +
                "        1.2.1 所有课程信息  \n" +
                "        1.2.2 指定课程名称的信息  \n" +
                "        1.2.3 指定老师的所有课程信息   \n" +
                "      1.3 查询老师信息  \n" +
                "        1.3.1 所有老师信息  \n" +
                "        1.3.2 指定老师信息  \n" +
                "    2. 新增  \n" +
                "      2.1 新增学生信息  \n" +
                "      2.2 新增课程信息     \n" +
                "    3. 修改    \n" +
                "      3.1 修改指定学生的成绩  \n" +
                "    4. 删除  \n" +
                "      4.1 删除指定学生  \n" +
                "      4.2 删除指定课程  \n" +
                "      4.3 删除指定老师 ";
    }
}
