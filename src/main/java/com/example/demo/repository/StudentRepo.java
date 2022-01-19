package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    /* 1. Returns the entry whose name is given
         as a method parameter. If no entry is found, this method
         returns an empty list */

      public List<Student> findByName(String name);

    /* 2.  Returns the number of entry whose name is given
          as a method parameter */

        public long countByName(String name);

    /* 3. Returns the distinct entries whose place is given
     as a method parameter. If no entries is found, this
     method returns an empty list. */

     public List<Student> findDistinctByPlace(String place);

    /* 4.*/
     @Query("SELECT t FROM Student t WHERE t.name = 'name'")
      public List<Student> findById();

    /*5. */
    @Query(value = "SELECT * FROM student t WHERE t.name = 'name'", nativeQuery=true)
    public List<Student> findByName();

    /*6. */
    @Query("SELECT t FROM Student t where t.name = ?1 AND t.place = ?2")
    public List<Student> findByNameAndPlace(String name, String place);

    /*7.
     @Query("SELECT t.name FROM Student where t.id = :id")
     String findNameById(@Param("id") Long id) */
}
