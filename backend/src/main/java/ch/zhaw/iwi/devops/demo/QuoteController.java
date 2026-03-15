package ch.zhaw.iwi.devops.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class QuoteController {

    @GetMapping("/services/quote")
public List<PathListEntry<Integer>> quote() {

    String[] quotes = {
        "Believe you can and you're halfway there.",
        "Success is not final, failure is not fatal.",
        "Small progress is still progress.",
        "Stay positive, work hard, make it happen.",
        "Don't watch the clock; do what it does. Keep going.",
        "The only way to do great work is to love what you do.",
        "Dream big and dare to fail.",
        "Don't be afraid to give up the good to go for the great.",
        "Success is not in what you have, but who you are.",
        "The secret of getting ahead is getting started.",
        "The best way to predict the future is to create it.",
        "The biggest risk is not taking any risk.",
        "Don't let yesterday take up too much of today."
    };
    
    Random r = new Random();

    var entry = new PathListEntry<Integer>();
    entry.setKey(1, "quoteKey");
    entry.setName(quotes[r.nextInt(quotes.length)]);

    List<PathListEntry<Integer>> result = new ArrayList<>();
    result.add(entry);

    return result;
    }
}
