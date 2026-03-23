package jp.gihyo.pro.java.tasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jp.gihyo.pro.java.tasklist.TaskListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
        record TaskItem(String id, String task, String deadline, boolean done) {
            // タスクアイテム情報を複数保持するレコードクラス
    }
    private List<TaskItem> taskItems = new ArrayList<>();
    //ArrayListクラスのインスタンスを生成して、タスクアイテムを格納するためのフィールドを用意。

    private final TaskListDao dao;

    @Autowired
    HomeController(TaskListDao dao) {
        this.dao = dao;
    }

    // タスクアイテムの 一覧表示のメソッド
    /*
        コントローラーで保持しているタスク一覧をModel経由でビューに渡し、
        Thymeleaf側で表示できる状態にしてからhome画面を返しています。
    */
    @GetMapping("/list")
    String listItem(Model model){
        List<TaskItem> taskItems = dao.findAll();
        model.addAttribute("TaskList", taskItems);
        //ModelクラスのaddAttributeメソッドを使って、taskItemsをビューに渡すためのTaskList属性を追加しています。
        return "home";
        //htmlファイルのhomeを返す
    }

    // タスクアイテムを追加機能のメソッド
    @GetMapping("/add")
    String addItem(@RequestParam("task") String task, @RequestParam("deadline") String deadline) {        
        String id = UUID.randomUUID().toString() .substring(0,8);
        TaskItem item = new TaskItem(id, task, deadline, false);
        dao.add(item);

        return "redirect:/list";
    }
}