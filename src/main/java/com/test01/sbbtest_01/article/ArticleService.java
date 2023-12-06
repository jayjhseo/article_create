package com.test01.sbbtest_01.article;

import com.test01.sbbtest_01.user.SiteUser;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public void create(String title, String content, SiteUser user) {
        Article a = new Article();
        a.setTitle(title);
        a.setContent(content);
        a.setAuthor(user);
        this.articleRepository.save(a);
    }

    public Article getArticle(Integer id) {
        Optional<Article> oa = this.articleRepository.findById(id);
        if (oa.isPresent()) {
            return oa.get();
        } else {
            throw new RuntimeException("article not found");
        }
    }
    public List<Article> getList() {
        return this.articleRepository.findAll();
    }
    public List<Article> getList(String kw) {
        Specification<Article> spec = search(kw);
        return this.articleRepository.findAll(spec);
    }

    public void modify(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);
        this.articleRepository.save(article);
    }

    public void delete(Article article) {
        this.articleRepository.delete(article);
    }

    private Specification<Article> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Article> a, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);
                Join<Article, SiteUser> u1 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(a.get("title"), "%" + kw + "%"),
                        cb.like(a.get("content"), "%" + kw + "%"),
                        cb.like(u1.get("username"), "%" + kw + "%"));
            }
        };
    }
}
