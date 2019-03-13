package io.pivotal.pal.tracker;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    Map<Long, TimeEntry> timeEntryMap = new HashMap<>();
    long currentId = 1l;

    public TimeEntry create(TimeEntry timeEntry){
        long id = currentId++;

        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntryMap.put(id, newTimeEntry);
        return newTimeEntry;
    }

    public TimeEntry find(long timeEntryId){
        return timeEntryMap.get(timeEntryId);
    }

    public List<TimeEntry> list(){
        List<TimeEntry> timeEntries = new ArrayList<>(timeEntryMap.values());
        return timeEntries;
    }

    public TimeEntry update(long timeEntryId, TimeEntry timeEntry){
        if(find(timeEntryId) == null) return null;

        TimeEntry updatdTimeEntry = new TimeEntry(timeEntryId,
            timeEntry.getProjectId(),
            timeEntry.getUserId(),
            timeEntry.getDate(),
            timeEntry.getHours()
        );

        timeEntryMap.replace(timeEntryId, updatdTimeEntry);
        return updatdTimeEntry;
    }

    public void delete(long timeEntryId){
        timeEntryMap.remove(timeEntryId);
    }
}
