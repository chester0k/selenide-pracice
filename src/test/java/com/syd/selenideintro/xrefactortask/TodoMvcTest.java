package com.syd.selenideintro.xrefactortask;

import com.syd.selenideintro.utils.Path;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Selenide.*;

public class TodoMvcTest {
    @Test
    void completeTask() {
        open("http://todomvc.com/examples/emberjs/");

        $x("//*[@id='new-todo']").setValue("a").pressEnter();
        $x("//*[@id='new-todo']").setValue("b").pressEnter();
        $x("//*[@id='new-todo']").setValue("c").pressEnter();
        $$x("//*[@id='todo-list']/li")
                .shouldHave(exactTexts("a", "b", "c"));

        $x("//*[@id='todo-list']/li[.//text()='b']//*" +
                Path.FilterBy.cssClass("toggle"))
                .click();
        $$x("//*[@id='todo-list']/li" +
                Path.FilterBy.cssClass("completed"))
                .shouldHave(exactTexts("b"));
        $$x("//*[@id='todo-list']/li" +
                Path.FilterBy.noCssClass("completed"))
                .shouldHave(exactTexts("a", "c"));
    }
}
