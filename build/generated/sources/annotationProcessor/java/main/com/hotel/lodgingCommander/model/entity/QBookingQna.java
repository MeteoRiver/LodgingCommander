package com.hotel.lodgingCommander.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookingQna is a Querydsl query type for BookingQna
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookingQna extends EntityPathBase<BookingQna> {

    private static final long serialVersionUID = 1801461030L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookingQna bookingQna = new QBookingQna("bookingQna");

    public final StringPath content = createString("content");

    public final QHotel hotel;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public final QUser user;

    public QBookingQna(String variable) {
        this(BookingQna.class, forVariable(variable), INITS);
    }

    public QBookingQna(Path<? extends BookingQna> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookingQna(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookingQna(PathMetadata metadata, PathInits inits) {
        this(BookingQna.class, metadata, inits);
    }

    public QBookingQna(Class<? extends BookingQna> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hotel = inits.isInitialized("hotel") ? new QHotel(forProperty("hotel"), inits.get("hotel")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

