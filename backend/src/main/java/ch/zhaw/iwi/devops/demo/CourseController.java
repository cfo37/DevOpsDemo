package ch.zhaw.iwi.devops.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CourseController {

    private Map<Integer, Course> Courses = new HashMap<Integer, Course>();

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        this.Courses.put(1, new Course(1, "DevOps", "Einführung in DevOps"));
        this.Courses.put(2, new Course(2, "Cloud", "Grundlagen Cloud Computing"));
        this.Courses.put(3, new Course(3, "Testing", "Tests und Qualitätssicherung"));
        System.out.println("Init Course Data");
    }

    @GetMapping("/services/course")
    public List<PathListEntry<Integer>> course() {
        var result = new ArrayList<PathListEntry<Integer>>();
        for (var course : this.Courses.values()) {
            var entry = new PathListEntry<Integer>();
            entry.setKey(course.getId(), "courseKey");
            entry.setName(course.getTitle());
            entry.getDetails().add(course.getDescription());
            entry.setTooltip(course.getDescription());
            result.add(entry);
        }
        return result.stream().sorted(Comparator.comparing(PathListEntry::getName)).toList();
    }

    @GetMapping("/services/course/{key}")
    public Course getCourse(@PathVariable("key") Integer key) {
        return this.Courses.get(key);
    }

    @PostMapping("/services/course")
    public void createCourse(@RequestBody Course course) {
        var newId = this.Courses.keySet().stream().max(Comparator.naturalOrder()).orElse(0) + 1;
        course.setId(newId);
        this.Courses.put(newId, course);
    }

    @PutMapping("/services/course/{key}")
    public void updateCourse(@PathVariable("key") Integer key, @RequestBody Course course) {
        course.setId(key);
        this.Courses.put(key, course);
    }

    @DeleteMapping("/services/course/{key}")
    public Course deleteCourse(@PathVariable("key") Integer key) {
        return this.Courses.remove(key);
    }
}
