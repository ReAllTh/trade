# Pull Request 规范（PR Specification）

请使用 `类型(作用域): 简短描述` 的格式，例如：
- `feature(user): user profile endpoint`
- `fix(db): transaction isolation level`

---

## 1. 变更类型 (Type of Change)

请选择最符合本次 PR 的一个或多个类型，并用 [X] 标记。

- [x] `feature`：用于实现新功能模块的功能（feature）分支。
- [ ] `fix`：用于 Bug 修复的修复（fix）分支。
- [ ] `refactor`：用于代码重构的重构（refactor）分支。
- [ ] `experience`：用于实现特性的实验（experience）分支。
- [ ] `doc`：用于文档变更的文档（doc）分支。s a feature)
- [ ] `chore`：用于其他维护性事务的杂务（chore）分支。

---

## 2. 动机与背景 (Motivation and Context)

请清晰描述本次变更的**原因**和**目的**。

为什么需要这个修改？它解决了什么问题？

*(如果变更很小，可以简述。如果变更复杂，请详细阐述动机。)*

---

## 3. 详细变更内容 (Detailed Changes)

请简要描述本次 PR 中**做了哪些具体修改**，以及**是如何实现**的。

- [ ] **[模块/文件]**: 详细说明修改内容 A
- [ ] **[模块/文件]**: 详细说明修改内容 B

*(如果涉及配置或基础设施，请说明具体修改了哪些配置项。)*

---

## 4. 破坏性变更 (Breaking Changes)

本次 PR 是否包含任何会破坏向后兼容性的重大变更（即依赖于旧代码的应用或模块会出错）？

- [ ] 是 (Yes)
- [ ] **否 (No)**

如果选择了“是”，请在下方详细描述破坏性变更的细节，并说明如何进行迁移。

---

## 5. 验证方法 (How Has This Been Tested?)

请描述您如何验证本次变更，确保其功能正确性和稳定性。

- [ ] **单元测试 (Unit Tests)**：已添加或更新相关的单元测试。
- [ ] **集成测试 (Integration Tests)**：已运行集成测试，并确认通过。
- [ ] **本地手动测试 (Manual Testing)**：请提供具体的测试步骤和环境（如：`mvn clean install` 验证父 POM 结构）。
- [ ] **无须测试 (Not Applicable)**：例如仅修改了文档或 `.gitignore`。

---

## 6. 关联 Issue (Related Issues)

请关联本次 PR 修复或相关的 Issue 编号。

*(例如: `Closes #123`, `Refs #456`)*