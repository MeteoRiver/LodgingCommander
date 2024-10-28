package com.hotel.lodgingCommander.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFacility is a Querydsl query type for Facility
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFacility extends EntityPathBase<Facility> {

    private static final long serialVersionUID = 21740478L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFacility facility = new QFacility("facility");

    public final BooleanPath airConditioning = createBoolean("airConditioning");

    public final BooleanPath airportShuttle = createBoolean("airportShuttle");

    public final BooleanPath bar = createBoolean("bar");

    public final BooleanPath breakfast = createBoolean("breakfast");

    public final BooleanPath evChargingStation = createBoolean("evChargingStation");

    public final BooleanPath freeParking = createBoolean("freeParking");

    public final BooleanPath freeWifi = createBoolean("freeWifi");

    public final BooleanPath gym = createBoolean("gym");

    public final QHotel hotel;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath laundryFacilities = createBoolean("laundryFacilities");

    public final BooleanPath nonSmoking = createBoolean("nonSmoking");

    public final BooleanPath petFriendly = createBoolean("petFriendly");

    public final BooleanPath restaurant = createBoolean("restaurant");

    public final BooleanPath spa = createBoolean("spa");

    public final BooleanPath swimmingPool = createBoolean("swimmingPool");

    public final BooleanPath twentyFourHourFrontDesk = createBoolean("twentyFourHourFrontDesk");

    public QFacility(String variable) {
        this(Facility.class, forVariable(variable), INITS);
    }

    public QFacility(Path<? extends Facility> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFacility(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFacility(PathMetadata metadata, PathInits inits) {
        this(Facility.class, metadata, inits);
    }

    public QFacility(Class<? extends Facility> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hotel = inits.isInitialized("hotel") ? new QHotel(forProperty("hotel"), inits.get("hotel")) : null;
    }

}

