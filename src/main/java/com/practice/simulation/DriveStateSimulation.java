package com.practice.simulation;

public class DriveStateSimulation {

    enum State {
        IDLE,    // 대기
        MOVING,  // 주행 중
        WAITING  // 손님 기다리는 중
    }

    // 간단 상태 전이
    public static State nextState(State current, String event) {
        switch (current) {
            case IDLE:
                if (event.equals("REQUEST")) return State.MOVING;
                break;
            case MOVING:
                if (event.equals("ARRIVE_PICKUP")) return State.WAITING;
                if (event.equals("CANCEL")) return State.IDLE;
                break;
            case WAITING:
                if (event.equals("BOARD")) return State.MOVING;
                if (event.equals("TIMEOUT")) return State.IDLE;
                break;
        }
        return current;
    }

    public static void main(String[] args) {
        State state = State.IDLE;
        String[] events = {
                "REQUEST",       // IDLE → MOVING
                "ARRIVE_PICKUP", // MOVING → WAITING
                "BOARD",         // WAITING → MOVING
                "CANCEL"         // MOVING → IDLE
        };

        for (String e : events) {
            state = nextState(state, e);
            System.out.println("이벤트: " + e + " → 상태: " + state);
        }
    }
}
