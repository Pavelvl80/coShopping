package com.model;

import javax.persistence.*;

/**
 * Created by Edvard Piri on 15.01.2017.
 */
@Entity
@Table(name = "FRIEND_RELATION")
public class FriendRelation {

    private Long Id;
    private Long fromUserId;
    private Long toUserId;

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
    public Long getFromUserId() {
        return fromUserId;
    }

    @ManyToOne
    @JoinColumn(name = "TO_USER_ID")
    public Long getToUserId() {
        return toUserId;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }
}
