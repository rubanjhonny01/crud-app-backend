package io.spring.mongo.demo.mongoDocuments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reviews")
@Data
@AllArgsConstructor @NoArgsConstructor
public class Review {
    private ObjectId id;
    private String body;

    private String rating;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Review(String body, String rating, LocalDateTime created, LocalDateTime updated) {
        this.body = body;
        this.rating = rating;
        this.created = created;
        this.updated = updated;
    }
}