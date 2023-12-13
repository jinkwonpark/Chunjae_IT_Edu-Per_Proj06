package com.pro06.repository.search;

import com.pro06.domain.Notice;
import com.pro06.domain.QNotice;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class NoticeSearchImpl extends QuerydslRepositorySupport implements NoticeSearch {

    public NoticeSearchImpl(){
        super(Notice.class);
    }

    @Override
    public Page<Notice> search1(Pageable pageable) {

        QNotice notice = QNotice.notice; // Q도메인 객체

        JPQLQuery<Notice> query = from(notice); // select.. from notice

        BooleanBuilder booleanBuilder = new BooleanBuilder(); // (

        booleanBuilder.or(notice.title.contains("11")); // title like ...

        booleanBuilder.or(notice.content.contains("11")); // content like ...

        // query.where(notice.title.contains("1")); // where title like ...

        query.where(booleanBuilder);
        query.where(notice.nno.gt(0L));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Notice> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<Notice> searchAll(String[] types, String keyword, Pageable pageable) {

        QNotice notice = QNotice.notice;
        JPQLQuery<Notice> query = from(notice);

        if( (types != null && types.length > 0) && keyword != null ){ // 검색 조건과 키워드가 있다면

            BooleanBuilder booleanBuilder = new BooleanBuilder(); // (

            for(String type: types){

                switch (type){
                    case "t":
                        booleanBuilder.or(notice.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(notice.content.contains(keyword));
                        break;
                }
            } // end for
            query.where(booleanBuilder);
        } // end if

        // nno > 0
        query.where(notice.nno.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Notice> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}