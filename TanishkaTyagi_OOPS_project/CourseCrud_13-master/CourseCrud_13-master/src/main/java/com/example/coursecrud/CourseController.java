package com.example.coursecrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    // 1. Get all courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return ResponseEntity.ok(courses);
    }

    // 2. Get course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseRepository.findById(id);

        if (course.isPresent()) {
            return ResponseEntity.ok(course.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 3. Create new course
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course savedCourse = courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

    // 4. Update existing course
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        Optional<Course> optionalCourse = courseRepository.findById(id);

        if (optionalCourse.isPresent()) {
            Course existingCourse = optionalCourse.get();

            // Update fields if provided in request
            if (courseDetails.getTitle() != null) {
                existingCourse.setTitle(courseDetails.getTitle());
            }
            if (courseDetails.getDescription() != null) {
                existingCourse.setDescription(courseDetails.getDescription());
            }
            if (courseDetails.getDuration() > 0) {
                existingCourse.setDuration(courseDetails.getDuration());
            }
            if (courseDetails.getPrice() >= 0) {
                existingCourse.setPrice(courseDetails.getPrice());
            }

            Course updatedCourse = courseRepository.save(existingCourse);
            return ResponseEntity.ok(updatedCourse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 5. Delete course
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return ResponseEntity.ok("Course deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
    }

    // 6. Search courses by title (custom query)
    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCourses(@RequestParam String title) {
        List<Course> courses = courseRepository.findByTitleContainingIgnoreCase(title);
        return ResponseEntity.ok(courses);
    }

    // 7. Get courses by max duration
    @GetMapping("/duration-less-than")
    public ResponseEntity<List<Course>> getCoursesByDuration(@RequestParam int maxDuration) {
        List<Course> courses = courseRepository.findByDurationLessThan(maxDuration);
        return ResponseEntity.ok(courses);
    }

    // 8. Get courses by price range
    @GetMapping("/price-range")
    public ResponseEntity<List<Course>> getCoursesByPriceRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {
        List<Course> courses = courseRepository.findByPriceBetween(minPrice, maxPrice);
        return ResponseEntity.ok(courses);
    }
}