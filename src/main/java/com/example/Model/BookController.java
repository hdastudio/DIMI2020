package com.example.Model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String index(ModelMap model) {
        model.addAttribute("books", bookService.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String add(Book book) {
        System.out.println("добавить " + book);
        bookService.addElem(book);
        return "redirect:/";
    }

    @GetMapping("/delete/{book_index}")
    public String delete(@PathVariable int book_index) {
        bookService.remove(book_index);
        return "redirect:/";
    }

    @GetMapping("/edit/{bookIndex}")
    public String editBookGet(@PathVariable int bookIndex, Model model) {
        Book book = bookService.getElem(bookIndex);
        model.addAttribute("book", book);
        return "/edit";
    }

    @PostMapping("/edit/{bookIndex}")
    public String editBookPost(@PathVariable int bookIndex, Book newBook) {
        bookService.editElement(bookIndex, newBook);
        return "redirect:/";
    }


}
