package ru.otus.mar.booklibrary.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.otus.mar.notification.client.NotificationClient;
import ru.otus.mar.notification.client.NotificationDto;

@Slf4j
@RequiredArgsConstructor
@Component
public class NotificationKafkaService {

    private final NotificationClient client;

    @KafkaListener(id = "booklibrary-microservice-author", topics = "postgres.booklibrary.author")
    public void authorListener(String author) {
        try {
            log.debug("Notice: {}", author);
            client.publishSilently(new NotificationDto("/topic/author", author));
        } catch (Exception e) {
            log.error(null, e);
        }
    }

    @KafkaListener(id = "booklibrary-microservice-genre", topics = "postgres.booklibrary.genre")
    public void genreListener(String genre) {
        try {
            log.debug("Notice: {}", genre);
            client.publishSilently(new NotificationDto("/topic/genre", genre));
        } catch (Exception e) {
            log.error(null, e);
        }
    }
}
