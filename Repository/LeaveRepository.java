package Repository;

import Models.LeaveRequests;
import Observer.ILeaveObserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LeaveRepository {
    private static LeaveRepository instance;
    private List<LeaveRequests> leaveRequests = new ArrayList<>();
    private List<ILeaveObserver> observers = new ArrayList<>();
    private int nextLeaveId = 1;
    private final String filePath = "leaves.txt";
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private LeaveRepository() {
        LoadData();
    }

    public static synchronized LeaveRepository getInstance() {
        if (instance == null) {
            instance = new LeaveRepository();
        }
        return instance;
    }

    public void AddObserver(ILeaveObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void NotifyObservers(LeaveRequests leave) {
        for (ILeaveObserver observer : observers) {
            observer.OnLeaveStatusChanged(leave);
        }
    }

    public void AddLeaveRequest(LeaveRequests leave) {
        leaveRequests.add(leave);
        SaveData();
        System.out.println("Leave request added successfully with ID: " + leave.getLeaveID());
        NotifyObservers(leave);
    }

    public void UpdateLeaveStatus(int leaveId, String newStatus) {
        LeaveRequests leave = GetLeaveByID(leaveId);
        if (leave != null) {
            leave.setStatus(newStatus);
            SaveData();
            System.out.println("Leave request ID " + leaveId + " status updated to " + newStatus);
            NotifyObservers(leave);
        } else {
            System.out.println("Leave request with ID " + leaveId + " not found.");
        }
    }

    public LeaveRequests GetLeaveByID(int leaveId) {
        return leaveRequests.stream()
                .filter(l -> l.getLeaveID() == leaveId)
                .findFirst()
                .orElse(null);
    }

    public List<LeaveRequests> GetLeaveByEmployeeID(int employeeId) {
        return leaveRequests.stream()
                .filter(l -> l.getEmployeeID() == employeeId)
                .toList();
    }

    public List<LeaveRequests> GetAllLeaveApplication() {
        return leaveRequests;
    }

    public int GenerateLeaveID() {
        return nextLeaveId++;
    }

    private void SaveData() {
        List<String> lines = new ArrayList<>();
        for (LeaveRequests leave : leaveRequests) {
            String startDateStr = leave.getStartDate() != null ? leave.getStartDate().format(dtf) : "";
            String endDateStr = leave.getEndDate() != null ? leave.getEndDate().format(dtf) : "";
            lines.add(leave.getLeaveID() + "|" + leave.getEmployeeID() + "|" + startDateStr + "|" + endDateStr + "|" + leave.getReason() + "|" + leave.getStatus());
        }
        try {
            Files.write(Paths.get(filePath), lines);
        } catch (IOException e) {
            System.err.println("Error saving leave data: " + e.getMessage());
        }
    }

    private void LoadData() {
        if (!Files.exists(Paths.get(filePath))) return;

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    LeaveRequests leave = new LeaveRequests();
                    leave.setLeaveID(Integer.parseInt(parts[0]));
                    leave.setEmployeeID(Integer.parseInt(parts[1]));
                    leave.setStartDate(LocalDate.parse(parts[2], dtf));
                    leave.setEndDate(LocalDate.parse(parts[3], dtf));
                    leave.setReason(parts[4]);
                    leave.setStatus(parts[5]);

                    leaveRequests.add(leave);
                    nextLeaveId = Math.max(nextLeaveId, Integer.parseInt(parts[0]) + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading leave data: " + e.getMessage());
        }
    }
}
