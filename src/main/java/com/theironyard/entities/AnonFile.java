package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by holdenhughes on 11/18/15.
 */
@Entity
@Table(name="files")
public class AnonFile {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    public int id;

    @Column(nullable = false)
    public String originalName;

    @Column(nullable = false)
    public String name;

   // @Column(nullable = false)
    public boolean isPerm;

    @Column(nullable = false)
    public String description;

}
