package jp.gihyo.pro.java.tasklist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/*
* タスク保持するエンドポイントです。
*/
@RestController
public class HomeRestContoroller {
    record TaskItem(String id, String task, String deadline, boolean done) {
    }
    private List<TaskItem> taskItems = new ArrayList<>();
    /*
    * タスク追加するエンドポイントです。
    */
    @GetMapping("/restadd")
    String addItem(@RequestParam("task") String task,@RequestParam("deadline") String deadline) {
        String id = UUID.randomUUID().toString() .substring(0,8);
        TaskItem item = new TaskItem(id, task, deadline, false);
        taskItems.add(item);

        return "タスクを追加しました。";
    }

    /*
    * タスク一覧表示するエンドポイントです。
    */
    @GetMapping("/restlist")
    String listItems(){
        String result = taskItems.stream().map(TaskItem::toString).collect(Collectors.joining(", "));
        return result;
    }
}