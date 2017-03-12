package com.model;

/**
 * Created by Edvard Piri on 08.03.2017.
 */
public enum Cities {
    Kiev("Kiev"),
    Harkov("Harkov"),
    Odessa("Odessa"),
    Dnepr("Dnepr"),
    Donesk("Donesk");

    private final String text;

    /**
     * @param text
     */
    Cities(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
