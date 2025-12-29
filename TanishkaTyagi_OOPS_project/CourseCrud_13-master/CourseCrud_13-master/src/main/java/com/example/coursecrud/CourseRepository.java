package com.example.coursecrud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Custom query methods (optional)

    // Find courses by title
    java.util.List<Course> findByTitleContainingIgnoreCase(String title);

    // Find courses by duration less than given value
    java.util.List<Course> findByDurationLessThan(int duration);

    // Find courses by price between min and max
    java.util.List<Course> findByPriceBetween(double minPrice, double maxPrice);
}