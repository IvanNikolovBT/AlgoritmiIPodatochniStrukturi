package LAB9.ZAD1;

import java.time.LocalTime;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read in the number of intervals
        int n = sc.nextInt();

        // Create a priority queue to store the end times of the intervals
        PriorityQueue<LocalTime> endTimes = new PriorityQueue<>();

        // Read in the start time and duration of each interval
        for (int i = 0; i < n; i++) {
            // Read in the start time
            String startTimeString = sc.next();
            LocalTime startTime = LocalTime.parse(startTimeString);

            // Read in the duration
            int duration = sc.nextInt();

            // Calculate the end time
            LocalTime endTime = startTime.plusMinutes(duration);
            if (endTime.isAfter(LocalTime.MAX)) {
                endTime = LocalTime.MAX;
            }

            // Add the end time to the priority queue
            endTimes.add(endTime);
        }

        // Initialize the maximum overlap to 0
        int maxOverlap = 0;

        // Initialize the current overlap count to 0
        int overlapCount = 0;

        // Keep track of the current time
        LocalTime currentTime = LocalTime.MIN;

        // Loop through the end times in the priority queue
        while (!endTimes.isEmpty()) {
            // Remove the next end time from the queue
            LocalTime endTime = endTimes.poll();

            // Check if the current time is before the end time
            if (currentTime.isBefore(endTime)) {
                // If the current time is before the end time, this interval overlaps with the others
                overlapCount++;
            } else {
                // If the current time is after or equal to the end time, this interval does not overlap
                // with the others. Reset the overlap count.
                overlapCount = 0;
            }

            // Update the maximum overlap if necessary
            maxOverlap = Math.max(maxOverlap, overlapCount);

            // Update the current time to the end time
            currentTime = endTime;
        }

        // Print the maximum overlap
        System.out.println(maxOverlap);
    }
}
