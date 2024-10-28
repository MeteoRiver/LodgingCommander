package com.hotel.lodgingCommander.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFixQna is a Querydsl query type for FixQna
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFixQna extends EntityPathBase<FixQna> {

    private static final long serialVersionUID = -921594518L;

    public static final QFixQna fixQna = new QFixQna("fixQna");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public QFixQna(String variable) {
        super(FixQna.class, forVariable(variable));
    }

    public QFixQna(Path<? extends FixQna> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFixQna(PathMetadata metadata) {
        super(FixQna.class, metadata);
    }

}

