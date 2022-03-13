package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.meetingentry.MeetingEntry;
import seedu.address.model.meetingentry.UniqueMeetingEntryList;

/**
 * Wraps all data of LinkyTime
 * Duplicates are not allowed (by .equals comparison)
 */
public class LinkyTime implements ReadOnlyLinkyTime {

    private final UniqueMeetingEntryList meetingEntries;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        meetingEntries = new UniqueMeetingEntryList();
    }

    public LinkyTime() {}

    /**
     * Creates a LinkyTime using the MeetingEntries in the {@code toBeCopied}
     */
    public LinkyTime(ReadOnlyLinkyTime toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the meeting entry list with {@code meetingEntries}.
     * {@code meetingEntries} must not contain duplicate meeting entries.
     */
    public void setMeetingEntries(List<MeetingEntry> meetingEntries) {
        this.meetingEntries.setMeetingEntries(meetingEntries);
    }

    /**
     * Resets the existing data of this {@code LinkyTime} with {@code newData}.
     */
    public void resetData(ReadOnlyLinkyTime newData) {
        requireNonNull(newData);

        setMeetingEntries(newData.getMeetingEntryList());
    }

    //// meeting-entry-level operations

    /**
     * Returns true if a meeting entry that is identical to {@code meetingEntry} exists in LinkyTime.
     */
    public boolean hasMeetingEntry(MeetingEntry meetingEntry) {
        requireNonNull(meetingEntry);
        return meetingEntries.contains(meetingEntry);
    }

    /**
     * Adds a meeting entry to LinkyTime.
     * The meeting entry must not already exist in LinkyTime.
     */
    public void addMeetingEntry(MeetingEntry m) {
        meetingEntries.add(m);
    }

    /**
     * Replaces the given meeting entry {@code target} in the list with {@code editedMeetingEntry}.
     * {@code target} must exist in LinkyTime.
     */
    public void setMeetingEntry(MeetingEntry target, MeetingEntry editedMeetingEntry) {
        requireNonNull(editedMeetingEntry);

        meetingEntries.setMeetingEntry(target, editedMeetingEntry);
    }

    /**
     * Removes {@code key} from {@code LinkyTime}.
     * {@code key} must exist in LinkyTime.
     */
    public void removeMeetingEntry(MeetingEntry key) {
        meetingEntries.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return meetingEntries.asUnmodifiableObservableList().size() + " meeting entries";
        // TODO: refine later
    }

    @Override
    public ObservableList<MeetingEntry> getMeetingEntryList() {
        return meetingEntries.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LinkyTime // instanceof handles nulls
                && meetingEntries.equals(((LinkyTime) other).meetingEntries));
    }

    @Override
    public int hashCode() {
        return meetingEntries.hashCode();
    }
}