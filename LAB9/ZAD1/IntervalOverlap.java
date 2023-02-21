package LAB9.ZAD1;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.PriorityQueue;
import java.util.Scanner;

public class IntervalOverlap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        // Create a priority queue to store end times of intervals
        PriorityQueue<LocalTime> endTimes = new PriorityQueue<>();

        int maxOverlap = 0;
        for (int i = 0; i < n; i++) {
            LocalTime startTime = LocalTime.parse(scanner.next() + ":00");
            long duration = scanner.nextLong();

            // Calculate end time by adding duration to start time
            LocalTime endTime = startTime.plus(duration, ChronoUnit.MINUTES);
            if (endTime.isAfter(LocalTime.MAX)) {
                endTime = LocalTime.MAX;
            }

            // Add end time to priority queue
            endTimes.add(endTime);

            // Remove any end times that have already passed
            while (!endTimes.isEmpty() && endTimes.peek().isBefore(startTime)) {
                endTimes.poll();
            }

            // Update maximum overlap
            maxOverlap = Math.max(maxOverlap, endTimes.size());
        }

        System.out.println(maxOverlap);
    }
}
