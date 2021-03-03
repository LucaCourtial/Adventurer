package org.adventurer.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
/*
  Information about the character's course
 */
public class Course {
    private String name;
    private List<String> steps;

    public Course(String name) {
        this.name = name;
    }

}
