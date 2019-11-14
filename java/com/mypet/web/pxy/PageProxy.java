package com.mypet.web.pxy;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.mypet.web.brd.ArticleMapper;
import com.mypet.web.cmm.ISupplier;
import com.mypet.web.utl.Printer;

import lombok.Data;
@Lazy@Data
@Component("pager")
public class PageProxy extends Proxy{
    private int totalCount, startRow, endRow,
    			pageCount, pageNum, pageSize, startPage, endPage, 
    			blockCount, blockNum, nextBlock, prevBlock;
	private String search;
    private final int BLOCK_SIZE = 5;
    private boolean existPrev, existNext;
    @Autowired Printer p;
    @Autowired ArticleMapper articleMapper;
    
    
    @SuppressWarnings("unused")
    public void paging() {
    	Supplier<Integer> s = ()-> articleMapper.countArticle();
    	totalCount = s.get();
    	System.out.println("프록시 안에서 찍은 전체글 갯수: "+totalCount);
    	pageCount = (totalCount%pageSize==0)?totalCount/pageSize:(totalCount/pageSize)+1;
    	startRow = (pageNum-1) * pageSize;
    	endRow = (pageNum==pageCount) ? totalCount -1 : startRow + pageSize -1;
    	blockCount = (pageCount%BLOCK_SIZE==0)?pageCount/BLOCK_SIZE:(pageCount/BLOCK_SIZE)+1;
    	blockNum = (pageNum - 1) / BLOCK_SIZE;
    	startPage = blockNum*BLOCK_SIZE + 1;
    	endPage = (BLOCK_SIZE * (blockNum + 1) > pageCount) ? pageCount : BLOCK_SIZE * (blockNum + 1);
    	existPrev = blockNum > 0;
    	existNext = blockNum < blockCount -1;
    	prevBlock = startPage - BLOCK_SIZE;
    	nextBlock = startPage + BLOCK_SIZE;
    }
}