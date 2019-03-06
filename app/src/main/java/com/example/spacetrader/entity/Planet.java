package com.example.spacetrader.entity;

import com.example.spacetrader.entity.Coordinate;

public enum Planet {
    A(5, 10, "ACAMAR"),
    B(50, 95, "BALOSNEE"),
    C(76, 2, "CALONDIA"),
    D(45, 41, "DALED"),
    E(77, 32, "ENDOR"),
    F(12, 66, "FERRIS"),
    G(34, 35, "GEMULON"),
    H(91, 87, "HADES"),
    I(82, 20, "IODINE"),
    J(22, 29, "JANUS"),
    K(10, 2, "KAYLON"),
    L(67, 76, "LAERTES"),
    M(99, 99, "MAGRAT"),
    N(0, 84, "NELVANA"),
    O(19, 17, "ODET"),
    P(55, 77, "PARADE"),
    Q(39, 4, "QUATOR"),
    R(61, 38, "RAKHAR"),
    S(26, 47, "SARPEIDON"),
    T(100, 18, "TALANI"),
    U(25, 90, "UMBERLEE"),
    V(7, 50, "VADERA"),
    W(38, 49, "WACK"),
    X(80, 74, "XENON"),
    Y(27, 63, "YEW"),
    Z(9, 40, "ZALKON");

    private int x;
    private int y;
    private String name;

    Planet(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Coordinate getCoordinates() {
        return new Coordinate(x, y);
    }

    public String getName() {
        return name;
    }
}
