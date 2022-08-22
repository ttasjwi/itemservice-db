package hello.itemservice.repository.jpa;

import hello.itemservice.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataJpaItemRepository extends JpaRepository<Item, Long> {

    /**
     * 상품 이름으로만 조회
     */
    List<Item> findByItemNameLike(String itemName);

    /**
     * 상품 최대 가격으로만 조회
     */
    List<Item> findByPriceLessThanEqual(Integer price);

    /**
     * 상품 이름, 최대 가격으로 조회(아래 메서드와 같은 기능 수행 - 쿼리 메서드 기능)
     */
    List<Item> findByItemNameLikeAndPriceLessThanEqual(String itemName, Integer price);

    /**
     * 상품 이름, 최대 가격으로 조회(위 메서드와 같은 기능 수행 - 쿼리 직접 실행)
     */
    @Query("SELECT i " +
            "FROM Item as i " +
            "WHERE i.itemName like :itemName and i.price <= :price")
    List<Item> findItems(@Param("itemName") String itemName, @Param("price") Integer price);
}
