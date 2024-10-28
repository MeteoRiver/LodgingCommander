package com.hotel.lodgingCommander.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookingList is a Querydsl query type for BookingList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookingList extends EntityPathBase<BookingList> {

    private static final long serialVersionUID = 10563996L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookingList bookingList = new QBookingList("bookingList");

    public final BooleanPath cancel = createBoolean("cancel");

    public final DatePath<java.time.LocalDate> checkInDate = createDate("checkInDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> checkOutDate = createDate("checkOutDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRoom room;

    public final NumberPath<Integer> totalPeople = createNumber("totalPeople", Integer.class);

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public final QUser user;

    public QBookingList(String variable) {
        this(BookingList.class, forVariable(variable), INITS);
    }

    public QBookingList(Path<? extends BookingList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookingList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookingList(PathMetadata metadata, PathInits inits) {
        this(BookingList.class, metadata, inits);
    }

    public QBookingList(Class<? extends BookingList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.room = inits.isInitialized("room") ? new QRoom(forProperty("room"), inits.get("room")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

