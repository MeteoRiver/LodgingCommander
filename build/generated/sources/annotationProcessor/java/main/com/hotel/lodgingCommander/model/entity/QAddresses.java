package com.hotel.lodgingCommander.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAddresses is a Querydsl query type for Addresses
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAddresses extends EntityPathBase<Addresses> {

    private static final long serialVersionUID = -1101213209L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAddresses addresses = new QAddresses("addresses");

    public final StringPath address = createString("address");

    public final StringPath addressDetail = createString("addressDetail");

    public final QHotel hotel;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final StringPath postCode = createString("postCode");

    public QAddresses(String variable) {
        this(Addresses.class, forVariable(variable), INITS);
    }

    public QAddresses(Path<? extends Addresses> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAddresses(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAddresses(PathMetadata metadata, PathInits inits) {
        this(Addresses.class, metadata, inits);
    }

    public QAddresses(Class<? extends Addresses> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hotel = inits.isInitialized("hotel") ? new QHotel(forProperty("hotel"), inits.get("hotel")) : null;
    }

}

