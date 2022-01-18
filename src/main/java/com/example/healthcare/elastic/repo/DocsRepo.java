package com.example.healthcare.elastic.repo;

import com.example.healthcare.elastic.index.Docs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocsRepo extends ElasticsearchRepository<Docs,String> {
}
