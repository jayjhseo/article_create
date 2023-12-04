package com.test01.sbbtest_01.article;

import com.test01.sbbtest_01.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public void modify(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);
        this.articleRepository.save(article);
    }

    public void delete(Article article) {
        this.articleRepository.delete(article);
    }
}
