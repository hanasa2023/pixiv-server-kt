# 🚀 Pixiv-server-kt
一个基于kotlin的提供HTTP API 获取Pixiv图片数据的服务器

## 服务器设置
| 名称   | 默认值       |
|------|-----------|
| host | "0.0.0.0" |
| port | 3836      |
* 你可以在```src/main/resources/application.conf```中更改端口


## HTTP API
### 获取图片信息
```GET``` ```/api/illust```

| 参数  | 类型  | 描述    | 默认值 |
|-----|-----|-------|-----|
| pid | int | 作品pid | 必填  |

返回值:

| 字段             | 类型           | 描述   |
|----------------|--------------|------|
| illust_details | IllustDetail | 图片详情 |
| author_details | AuthorDetail | 作者详情 |

```IllustDetail```:

| 字段              | 类型          | 描述     |
|-----------------|-------------|--------|
| url             | string      | 图片地址   |
| tags            | string[]    | 图片标签   |
| illust_images   | IllustImage | 图片信息   |
| manga_a         | MangaA[]?   | 漫画信息   |
| url_s           | string      | 缩略图地址  |
| url_ss          | string      | 小缩略图地址 |
| url_big         | string      | 原图地址   |
| url_placeholder | string      | 占位图地址  |

```AuthorDetail```:

| 字段           | 类型     | 描述   |
|--------------|--------|------|
| user_id      | string | 用户id |
| user_name    | string | 用户名  |
| user_account | string | 用户账号 |

```IllustImage```:

| 字段                  | 类型     | 描述   |
|---------------------|--------|------|
| illust_image_width  | string | 图片宽度 |
| illust_image_height | string | 图片高度 |

```MangaA```:

| 字段        | 类型     | 描述    |
|-----------|--------|-------|
| page      | string | 漫画页数  |
| url       | string | 漫画地址  |
| url_small | string | 缩略图地址 |
| url_big   | string | 原图地址  |

示例
```http request 
GET /api/illust?pid=110165932
```

```json
{
  "illust_details": {
    "url": "https://i.pximg.net/c/600x1200_90/img-master/img/2023/07/23/15/19/47/110165932_p0_master1200.jpg",
    "tags": [
      "漫画",
      "少女",
      "可愛い",
      "吉他",
      "孤独摇滚",
      "原创",
      "孤独ロック",
      "波奇酱",
      "粉髪",
      "女の子"
    ],
    "illust_images": [
      {
        "illust_image_width": "1550",
        "illust_image_height": "2465"
      },
      {
        "illust_image_width": "1550",
        "illust_image_height": "2582"
      }
    ],
    "manga_a": [
      {
        "page": 0,
        "url": "https://i.pximg.net/img-master/img/2023/07/23/15/19/47/110165932_p0_master1200.jpg",
        "url_small": "https://i.pximg.net/c/100x100/img-master/img/2023/07/23/15/19/47/110165932_p0_master1200.jpg",
        "url_big": "https://i.pximg.net/img-original/img/2023/07/23/15/19/47/110165932_p0.jpg"
      },
      {
        "page": 1,
        "url": "https://i.pximg.net/img-master/img/2023/07/23/15/19/47/110165932_p1_master1200.jpg",
        "url_small": "https://i.pximg.net/c/100x100/img-master/img/2023/07/23/15/19/47/110165932_p1_master1200.jpg",
        "url_big": "https://i.pximg.net/img-original/img/2023/07/23/15/19/47/110165932_p1.jpg"
      }
    ],
    "url_s": "https://i.pximg.net/c/360x360_70/img-master/img/2023/07/23/15/19/47/110165932_p0_square1200.jpg",
    "url_ss": "https://i.pximg.net/c/48x48/img-master/img/2023/07/23/15/19/47/110165932_p0_square1200.jpg",
    "url_big": "https://i.pximg.net/img-original/img/2023/07/23/15/19/47/110165932_p0.jpg",
    "url_placeholder": "https://i.pximg.net/c/100x100/img-master/img/2023/07/23/15/19/47/110165932_p0_master1200.jpg"
  },
  "author_details": {
    "user_id": "25799681",
    "user_name": "南以颜语",
    "user_account": "user_srvp7233"
  }
}
```

### 搜索图片
```POST``` ```/api/search```

返回值：


| 字段       | 类型     | 描述    | 默认值      |
|----------|--------|-------|----------|
| tag      | string | 搜索标签  | 必填       |
| order    | string | 搜索规则  | date     |
| blt      | int    | 最少收藏数 | 0        |
| mode     | string | 搜索模式  | full_tag |
| restrict | string | 搜索限制  | safe     |

* ```order```:
  * ```date```: 按照时间排序（降序）
  * ```popular```: 按照热度排序（降序）


* ```mode```:
  * ```full_tag```: 完全匹配标签
  * ```tag```: 包含标签


* ```restrict```:
 * ```safe```: 安全模式
 * ```r18```: R18/R18-G模式
 * ```all```: 无限制

示例：
```http request
POST /api/search
Content-Type: application/json

{
    "tag": "夜夜",
    "order": "popular",
    "blt": 1,
    "mode": "tag",
    "restrict": "all"
}
```

```json
{
  "illust": {
    "data": [
      {
        "id": "118543896",
        "title": "落書き",
        "tags": [
          "白布賢二郎",
          "赤葦京治",
          "夜夜久衛輔",
          "ハイキュー"
        ],
        "userId": "62500616",
        "userName": "ぽむる",
        "width": 768,
        "height": 1024,
        "createDate": "2024-05-08T13:02:16+09:00",
        "updateDate": "2024-05-08T13:02:16+09:00",
        "aiType": 1
      },
      {
        "id": "117353086",
        "title": "家妻~女儿~",
        "tags": [
          "夜夜",
          ...
        ],
        "userId": "19043872",
        "userName": "酱の沫花名丶",
        "width": 1280,
        "height": 1856,
        "createDate": "2024-03-29T17:15:06+09:00",
        "updateDate": "2024-03-29T17:15:06+09:00",
        "aiType": 2
      },
      ...
    ]
  }
}
```