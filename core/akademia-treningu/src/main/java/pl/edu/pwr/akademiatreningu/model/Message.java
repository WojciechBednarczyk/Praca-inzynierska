package pl.edu.pwr.akademiatreningu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer id;

    private String message;

    @Column(name = "date_of_sent")
    private Date dateOfSent;

    @ManyToOne
    @JoinColumn(name = "user_sender_id", nullable = false)
    private User userSender;

    @ManyToOne
    @JoinColumn(name = "user_receiver_id", nullable = false)
    private User userReceiver;
}
