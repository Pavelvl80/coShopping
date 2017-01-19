package com.model;

import javax.persistence.*;

/**
 * Created by Edvard Piri on 15.01.2017.
 */
@Entity
@Table(name = "FRIEND_RELATION")
public class FriendRelation {

    private Long fromUserId;
    private Long toUserId;

    public FriendRelation() {
    }

    @ManyToOne
    @JoinColumn(name = "FROM_USER_ID")
    public Long getFromUserId() {
        return fromUserId;
    }

    @ManyToOne
    @JoinColumn(name = "FROM_USER_ID")
    public Long getToUserId() {
        return toUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }
}
