package com.hotel.lodgingCommander.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 745864806L;

    public static final QUser user = new QUser("user");

    public final ListPath<BookingQna, QBookingQna> bookingQnas = this.<BookingQna, QBookingQna>createList("bookingQnas", BookingQna.class, QBookingQna.class, PathInits.DIRECT2);

    public final ListPath<Cart, QCart> carts = this.<Cart, QCart>createList("carts", Cart.class, QCart.class, PathInits.DIRECT2);

    public final EnumPath<com.hotel.lodgingCommander.model.entity.enums.UserGrade> grade = createEnum("grade", com.hotel.lodgingCommander.model.entity.enums.UserGrade.class);

    public final ListPath<Hotel, QHotel> hotels = this.<Hotel, QHotel>createList("hotels", Hotel.class, QHotel.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<LikeList, QLikeList> likeLists = this.<LikeList, QLikeList>createList("likeLists", LikeList.class, QLikeList.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final ListPath<Review, QReview> reviews = this.<Review, QReview>createList("reviews", Review.class, QReview.class, PathInits.DIRECT2);

    public final EnumPath<com.hotel.lodgingCommander.model.entity.enums.UserRole> role = createEnum("role", com.hotel.lodgingCommander.model.entity.enums.UserRole.class);

    public final StringPath tel = createString("tel");

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

