package com.peng.news.controller.management;

import com.peng.news.model.Result;
import com.peng.news.model.po.CollectionPO;
import com.peng.news.model.vo.CollectionVO;
import com.peng.news.service.CollectionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CollectionController$
 *
 * @author shan
 * @date 2023/3/3$
 */

@Api(tags = "新闻收藏接口")
@RestController
@RequestMapping("/management/collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @GetMapping("/listByUserId")
    public Result<List<CollectionVO>> listByUserId(){
        return Result.success( collectionService.listCollectionPO());
    }

    @GetMapping("/rmCollectionById/{id}")
    public Result rmCollectionById(@PathVariable Integer id){
        return Result.success(collectionService.delete(id));
    }

    @PostMapping("/addCollection")
    public Result addCollection( CollectionPO collectionPO){
        if (collectionService.add(collectionPO)!=null) {
            return Result.success(collectionService.add(collectionPO));
        }
        return Result.fail("该新闻已经收藏，请不要再次收藏！");
    }
}
