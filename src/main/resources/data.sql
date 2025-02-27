CREATE TABLE IF NOT EXISTS jobs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    salary VARCHAR(100),
    location VARCHAR(100),
    job_type VARCHAR(50),
    education VARCHAR(50),
    description TEXT,
    featured_count INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    INDEX idx_featured_count (featured_count),
    INDEX idx_created_at (created_at)
);

INSERT INTO jobs (title, company_name, salary, location, job_type, education, description, featured_count, created_at) VALUES 
('Java开发实习生', '阿里巴巴', '300-400/天', '杭州', '全职实习', '本科及以上', 'Java开发实习生岗位...', 0, NOW()),
('前端开发实习生', '腾讯', '250-350/天', '深圳', '全职实习', '本科及以上', '前端开发实习生岗位...', 0, NOW()),
('算法工程师实习生', '字节跳动', '400-500/天', '北京', '全职实习', '硕士及以上', '算法工程师实习生岗位...', 0, NOW()),
('测试开发实习生', '美团', '200-300/天', '北京', '全职实习', '本科及以上', '测试开发实习生岗位...', 0, NOW()),
('运维开发实习生', '网易', '250-350/天', '杭州', '全职实习', '本科及以上', '运维开发实习生岗位...', 0, NOW()),
('数据分析实习生', '京东', '300-400/天', '北京', '全职实习', '本科及以上', '数据分析实习生岗位...', 0, NOW()),
('产品经理实习生', '百度', '300-350/天', '北京', '全职实习', '本科及以上', '产品经理实习生岗位...', 0, NOW()),
('UI设计实习生', '小米', '200-300/天', '北京', '全职实习', '本科及以上', 'UI设计实习生岗位...', 0, NOW()),
('安卓开发实习生', 'OPPO', '250-350/天', '深圳', '全职实习', '本科及以上', '安卓开发实习生岗位...', 0, NOW()),
('IOS开发实习生', 'vivo', '250-350/天', '深圳', '全职实习', '本科及以上', 'IOS开发实习生岗位...', 0, NOW()),
('运营实习生', '滴滴', '200-300/天', '北京', '全职实习', '本科及以上', '运营实习生岗位...', 0, NOW()),
('人力资源实习生', '快手', '200-250/天', '北京', '全职实习', '本科及以上', '人力资源实习生岗位...', 0, NOW()),
('市场营销实习生', '华为', '250-300/天', '深圳', '全职实习', '本科及以上', '市场营销实习生岗位...', 0, NOW()),
('财务实习生', '小红书', '200-250/天', '上海', '全职实习', '本科及以上', '财务实习生岗位...', 0, NOW()); 