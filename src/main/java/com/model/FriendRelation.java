package com.model;

import javax.persistence.*;

/**
 * Created by Edvard Piri on 15.01.2017.
 */
@Entity
@Table(name = "FRIEND_RELATION")
public class FriendRelation {

    private Long Id;
    private Users fromUser;
    private Users toUser;

    public FriendRelation() {
    }

    @Id
    @SequenceGenerator(name = "FRIEND_RELATION_SEQ", sequenceName = "FRIEND_RELATION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FRIEND_RELATION_SEQ")
    public Long getId() {
        return Id;
    }

    @ManyToOne
    @JoinColumn(name = "FROM_USER_ID")
    public Users getFromUserId() {
        return fromUser;
    }

    @ManyToOne
    @JoinColumn(name = "TO_USER_ID")
    public Users getToUserId() {
        return toUser;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setFromUserId(Users fromUserId) {
        this.fromUser = fromUserId;
    }

    public void setToUserId(Users toUserId) {
        this.toUser = toUserId;
    }
}
